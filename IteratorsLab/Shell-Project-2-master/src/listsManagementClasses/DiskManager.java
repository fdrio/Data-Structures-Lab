package listsManagementClasses;

import java.awt.geom.Point2D;

import diskUtilities.DiskUnit;
import diskUtilities.DiskUtils;
import diskUtilities.VirtualDiskBlock;
import disk_Exceptions.FullDirectoryException;
import disk_Exceptions.FullDiskException;
import lists.INode;

public class DiskManager {
	DiskUnit diskUnit;
	private INode currentDirectory;
	private String mountName; //name of mounted disk

	private int capacity;
	private int blockSize;
	private int freeBlockIndex;
	private int endOfFreeBlockIndex;
	private int firstFreeINode;
	private int totalINodes;

	public DiskManager(){}

	public void prepareDiskUnit(String diskName){ //prepares recently created DiskUnit
		//constants; will always be in the diskunit regardless if it was created recently.
		diskUnit = DiskUnit.mount(diskName);

		capacity = diskUnit.getCapacity();
		blockSize = diskUnit.getBlockSize();
		freeBlockIndex = diskUnit.getFreeBlockIndex();
		endOfFreeBlockIndex = diskUnit.getEndOfFreeBlockIndex();
		firstFreeINode = diskUnit.getFirstFreeINode();
		totalINodes = diskUnit.getTotalINodes();


		formatFreeBlockSpace(); //prepare free blocks in DiskUnit
		formatINodeSpace(); // prepare INodes in DiskUnit
		int rootFileSize = blockSize*4; //test size for root file in directory
		setINodeAtIndex(blockSize,new INode((byte)0,rootFileSize,prepareFreeBlocksForUse(rootFileSize))); //create root file's INode.
		stop();
		diskUnit.shutdown();
	}

	public void mount(String diskName){
		diskUnit = DiskUnit.mount(diskName);

		capacity = diskUnit.getCapacity();
		blockSize = diskUnit.getBlockSize();
		freeBlockIndex = diskUnit.getFreeBlockIndex();
		endOfFreeBlockIndex = diskUnit.getEndOfFreeBlockIndex();
		firstFreeINode = diskUnit.getFirstFreeINode();
		totalINodes = diskUnit.getTotalINodes();


		currentDirectory = getINodeAtIndex(blockSize);
		mountName = diskName;
	}

	////////formatters////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void formatINodeSpace(){
		int remainingINodes = totalINodes;
		int nextINode = blockSize+9;
		int currentBlock = 0;
		int currentIndex = 5;
		while (remainingINodes>0){
			int maxINodesPerBlock = blockSize/9;
			VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
			while (maxINodesPerBlock>1){
				DiskUtils.copyIntToBlock(vdb, currentIndex, nextINode);
				currentIndex+=9;
				nextINode+=9;
				maxINodesPerBlock--;
				remainingINodes--;
			}
			remainingINodes--;
			if (remainingINodes==0){
				return;
			}
			currentBlock++;
			nextINode = (currentBlock*blockSize);
			DiskUtils.copyIntToBlock(vdb, currentIndex, nextINode);
			diskUnit.write(currentBlock, vdb);
			currentIndex=5;
		}
	}


	private void formatFreeBlockSpace(){
		int totalINodeBlocks = (int)Math.ceil((double)capacity/100);
		int stillFreeSpace = capacity-totalINodeBlocks-1; //Amount of FreeBlocks left in an empty DiskUnit
		int currentBlock = totalINodeBlocks+1; //block after iNodes
		while (stillFreeSpace>0){ //while there is still free space
			registerFreeBlocks(currentBlock); //register the next block
			currentBlock+=1; //go to the next block
			stillFreeSpace--; //reduce the amount remaining by 1 block
		}
	}


	/////////File Managers/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean fileExists(String fileName){
		boolean atEnd = false;
		int currentIndex = currentDirectory.getIndex();//start at current directory
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		while (!atEnd){ // while still within directory
			diskUnit.read(currentIndex, vdb); //read current block in directory
			int counter=0; //counter for total bytes in blocks
			while(counter<blockSize){//there are still files in directory
				String tempName = ""; //empty string to concat chars.
				int stringCounter=0; //counter for current string
				while(stringCounter<20){
					if(DiskUtils.getCharFromBlock(vdb, counter)=='#'){//flag for end of name.
						break;//file name has been completely read
					}else{ //concat the char to tempName
						tempName = tempName+DiskUtils.getCharFromBlock(vdb, counter);
						stringCounter++;
					}
				}
				if (tempName.equals(fileName)){//at file.
					return true;
				}
				counter+=24;//advance to next file
			}
			currentIndex = DiskUtils.getIntFromBlock(vdb, blockSize-4); //get next block in file by reading end of block's int
			if(currentIndex==0){ //if end int is 0, there are no more blocks in file.
				atEnd=true;
			}
		}
		return false;
	}

//	public boolean loadFile(String fileName,String newName){
//		boolean atEnd = false;
//		int currentIndex = currentDirectory.getIndex();//start at current directory
//		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
//		while (!atEnd){ // while still within directory
//			diskUnit.read(currentIndex, vdb); //read current block in directory
//			int counter=0; //counter for total bytes in blocks
//			while(counter<blockSize){//there are still files in directory
//				String tempName = ""; //empty string to concat chars.
//				int stringCounter=0; //counter for current string
//				while(stringCounter<20){
//					if(DiskUtils.getCharFromBlock(vdb, counter)=='#'){//flag for end of name.
//						break;//file name has been completely read
//					}else{ //concat the char to tempName
//						tempName = tempName+DiskUtils.getCharFromBlock(vdb, counter);
//						stringCounter++;
//					}
//				}
//				if (tempName.equals(fileName)){//at file.
//					INode ntr = getINodeAtIndex(DiskUtils.getIntFromBlock(vdb, counter+19));
//					copyFile(ntr,newName);
//				}
//				counter+=24;//advance to next file 
//
//			}
//			currentIndex = DiskUtils.getIntFromBlock(vdb, blockSize-4); //get next block in file by reading end of block's int
//			if(currentIndex==0){ //if end int is 0, there are no more blocks in file.
//				atEnd=true;
//			}
//		}
//		return false;
//	}


	//		public String getFileNameAtIndex(int index){//must advance pointer 44 bytes
	//			String fileName = "";
	//			try{
	//				for (int x=0;x<40;x++){
	//					long currentPos = diskUnitRAF.getFilePointer();
	//					if(!(diskUnitRAF.readByte()==0)){//reads one byte and checks if the space is free
	//						diskUnitRAF.seek(currentPos); //if it wasnt free, go back to read correctly
	//						fileName = fileName+diskUnitRAF.readChar();
	//					}
	//					diskUnitRAF.readByte(); //if it was free, read the extra byte it would have to advance the pointer
	//				}
	//				diskUnitRAF.readInt();//just to skip int and advance the pointer.
	//			}catch (Exception e){}
	//			return fileName;
	//		}
	//	
	//		public void addFileToDirectory(byte type,int fileSize,String fileName) throws FullDirectoryException, FullDiskException{
	//			INode fileToAdd =  new INode(type, fileSize ,prepareFreeBlocksForUse(fileSize));
	//			if(diskUnit.getFirstFreeINode()==-1){//disk is full
	//				throw new FullDiskException ("No more available space in disk!");
	//			}
	//			int index = getDirectorySpace();
	//			if (index==-1){
	//				throw new FullDirectoryException("No more available space in this directory!");
	//			}else{
	//				int INodeIndex = addINode(fileToAdd); //adds to linked list
	//				writeFileToDirectory(fileName, INodeIndex, index); //Adds to RAF
	//			}
	//		}
	//	
	//	
	//	
	//		
	//		
	//		private void writeFileToDirectory(String fileName, int INodeIndex, int directoryIndex){
	//			try{
	//				diskUnitRAF.seek(directoryIndex);
	//				for (int x=0; x<fileName.length();x++){
	//					diskUnitRAF.writeChar(fileName.charAt(x));
	//				}
	//				diskUnitRAF.seek(directoryIndex+40);
	//				diskUnitRAF.writeInt(INodeIndex);
	//			}catch (Exception e){}
	//		}
	//
	//
	//	
	//		private int getDirectorySpace() { //get index of space within directory
	//			boolean atEnd=false;
	//			int currentIndex = currentDirectory.getIndex();
	//			int filesPerBlock = diskUnit.getBlockSize()/24; //20 bytes for chars, 4 for iNode index int
	//			int filesPerBlock = blockSize/44; //40; 2 for each char; 4 for the INode index
	//			try{
	//				while (!atEnd){ // while still within directory
	//					diskUnitRAF.seek(currentIndex); //start of block
	//					for (int x=0; x<filesPerBlock;x++){ //for each possible file
	//						diskUnitRAF.skipBytes(40); //skip the first 40 characters
	//						if (diskUnitRAF.readByte()==0){ //check if file space has an INode allocated to it. 0 = no INode
	//							return (int)diskUnitRAF.getFilePointer()-41; //if not, space is free. return index.
	//						}else{
	//							diskUnitRAF.skipBytes(3);//skip remaining bytes to get to next file.
	//						}
	//					}
	//					diskUnitRAF.seek(currentIndex+blockSize-4); //check where the next block is
	//					currentIndex=diskUnitRAF.readInt();
	//					if (currentIndex==0){ //if next block index is 0, no more remaining blocks
	//						atEnd=true;
	//					}
	//				}
	//			}catch (Exception e){}
	//			return -1; //no free space in directory was found.
	//	}



	//////////free block managers/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getNextFreeBlock() throws FullDiskException{
		int availableBlockIndex; 
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		if (freeBlockIndex==0){ // the disk is full.
			throw new FullDiskException ("Disk is full!");
		}
		else if (endOfFreeBlockIndex!=4){ //if there are available indexes to choose from
			diskUnit.read(freeBlockIndex, vdb); //gets the free block array
			endOfFreeBlockIndex-=4; // move pointer to the next index in the array
			availableBlockIndex = DiskUtils.getIntFromBlock(vdb, endOfFreeBlockIndex); //end of free block index points to next available position
		}else{ //the only available block is this one.
			availableBlockIndex = freeBlockIndex; // this block is the next one that's available;
			diskUnit.read(freeBlockIndex, vdb);
			freeBlockIndex = DiskUtils.getIntFromBlock(vdb, 0); //next free block array is at root of this block
			endOfFreeBlockIndex = blockSize; //freeBlockIndex+blockSize would be the end of this array.
		}
		return availableBlockIndex;
	}


	public void registerFreeBlocks(int block) {//check if copy to block doesnt rewrite what was previously on
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		if (freeBlockIndex == 0)  { //There were no free blocks previous to this
			DiskUtils.copyIntToBlock(vdb, 0, 0); //Copy 0 to root of this block.
			freeBlockIndex=block; // This new registered block will become tree root.
			endOfFreeBlockIndex = 4; //The array is currently empty; so the next available free block is itself.
			diskUnit.write(block, vdb);
		}  
		else if (endOfFreeBlockIndex>=blockSize) {      // the root node in the tree is full
			DiskUtils.copyIntToBlock(vdb, 0, freeBlockIndex); //Copy parent as root of this block
			freeBlockIndex=block; //Next free block will be picked from the subtree. 
			endOfFreeBlockIndex=4; //The array is currently empty; so the next available free block is itself.
			diskUnit.write(block, vdb);
		}  
		else { //there is space on the current subtree root.
			diskUnit.read(freeBlockIndex, vdb); //get all previous block information
			DiskUtils.copyIntToBlock(vdb, endOfFreeBlockIndex, block); //Copy next free block's index onto this block
			endOfFreeBlockIndex+=4; //the current index will have an int; move it to the next index.
			diskUnit.write(freeBlockIndex, vdb); //return original block plus new data.
		}
	}     

	private int prepareFreeBlocksForUse(int fileSize) {//returns index of first block. Gets as many free blocks as the file needs and sets links up.
		int totalBlocksUsed = (int)Math.ceil((double)fileSize/((double)blockSize-4.0));//formula used to determine how many blocks per file depending on file's size.
		int currentBlock = 0;
		int btr = 0;//for error
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		for(int x=0;x<totalBlocksUsed;x++){ // for each block needed, register a new free block.
			int index = getNextFreeBlock();
			if (x==0){//save the first file's block
				btr = index;
			}
			DiskUtils.copyIntToBlock(vdb, blockSize-4, currentBlock); //write previous block's reference into current node's last int
			diskUnit.write(index, vdb);
			currentBlock = index;
		}
		return btr;
	}

	//////INode Managers//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	private void reclaimINode(int iNodeIndex){//returns iNode to used linked list. 
		int index =(int) getBlockIndexWithPosition(iNodeIndex).getY();
		int blockPos = (int)getBlockIndexWithPosition(iNodeIndex).getX();
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		diskUnit.read(blockPos, vdb);
		int currentFreeINodeIndex = firstFreeINode;
		DiskUtils.copyIntToBlock(vdb, index+5, currentFreeINodeIndex); //sets this iNode's next to the head
		firstFreeINode = iNodeIndex; //this iNode is the new head.
	}

	private INode getINodeAtIndex(int iNodeIndex) { //returns iNode at this index.
		int index =(int) getBlockIndexWithPosition(iNodeIndex).getY();
		int blockPos = (int)getBlockIndexWithPosition(iNodeIndex).getX();
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		diskUnit.read(blockPos, vdb);
		firstFreeINode=DiskUtils.getIntFromBlock(vdb, index+5);
		return new INode(vdb.getElement(index),DiskUtils.getIntFromBlock(vdb, index+1),DiskUtils.getIntFromBlock(vdb, index+5));
	}
	private void setINodeAtIndex(int iNodeIndex, INode nta) { //sets new iNode given a free i node index.
		int index =(int) getBlockIndexWithPosition(iNodeIndex).getY();
		int blockPos = (int)getBlockIndexWithPosition(iNodeIndex).getX();
		VirtualDiskBlock vdb = new VirtualDiskBlock(blockSize);
		diskUnit.read(blockPos, vdb); //save previous information
		firstFreeINode=DiskUtils.getIntFromBlock(vdb, index+5); //set the new free iNode head using the "getNext()"

		vdb.setElement(index,nta.getType()); //Set new type
		DiskUtils.copyIntToBlock(vdb, index+1,nta.getSize()); //set new Size
		DiskUtils.copyIntToBlock(vdb, index+5,nta.getIndex()); //set new Index
		diskUnit.write(blockPos, vdb);
	}
	private void addINode(INode nta) { //sets new iNode given a free i node index.
		setINodeAtIndex(firstFreeINode,nta);
	}



	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getDiskName(){
		return mountName;
	}
	public boolean isMounted(){
		return mountName!=null;
	}

	private Point2D getBlockIndexWithPosition(int totalByteIndex){//first is block index, second is index within that block's array.
		int index = (totalByteIndex%blockSize);
		return new Point2D.Double((int)Math.floor(index/blockSize),index);
	}

	public void stop(){

		diskUnit.setFreeBlockIndex(freeBlockIndex);
		diskUnit.setEndOfFreeBlockIndex(endOfFreeBlockIndex);
		diskUnit.setFirstFreeINode(firstFreeINode);

		mountName=null;
		currentDirectory=null;
	}

}
