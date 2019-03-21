package kr.co.common;

public class ListPager {
	
	private int pageCnt;		//전체 페이지 수
	private int listCnt;			//전체 리스트 수
	private int curPage;		//현재 페이지
	private int pageBegin;		//현재 페이지의 시작 번호
	private int pageEnd;		//현재 페이지의 끝 번호
	private int prevPage;		//이전 페이지
	private int nextPage;		//다음 페이지
	private int blockCnt;		//전체 페이지 블록 수
	private int blockBegin;		//현재 페이지 블록의 시작번호
	private int blockEnd;		//현재 페이지 블록의 끝번호
	private int curBlock;		//현재 페이지 블록
	private int nextBlock;		//다음 블록 첫 페이지
	private int prevBlock;		//이전 블록 첫 페이지
	
	public ListPager(int listCnt, int curPage){
		this.curPage = curPage;
		this.listCnt = listCnt;
		setPageCnt(listCnt);		//전체 페이지 수 구하기
		setCountBlock();			//전체 페이지 블록 수 구하기
		setBlockRange();			//현재 블록, 페이지 블록의 시작, 끝 번호, 다음 페이지 번호, 이전 페이지 번호 구하기
	}

	public void setBlockRange() {
		//현재 블록 계산
		curBlock = curPage / 10;
		if(curPage % 10 != 0){
			curBlock = curBlock + 1;
		}
		
		//블록 시작, 끝 번호 계산
		blockBegin = (curBlock - 1) * 10 + 1;
		blockEnd = blockBegin + 10 - 1;
		if(blockEnd > pageCnt){
			blockEnd = pageCnt;
		}
		
		//다음, 이전 블록 시작 번호 계산
		nextBlock = blockBegin + 10;
		prevBlock = blockEnd - 10;
		if(nextBlock > pageCnt) {
			nextBlock = pageCnt;
		}
		if(prevBlock < 1) {
			prevBlock = 1;
		}
		
		//다음, 이전 페이지 계산
		prevPage = curPage - 1;
		nextPage = curPage + 1;
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
		if(prevPage < 1) {
			prevPage = 1;
		}
		
		//페이지 시작, 끝 번호 계산
		pageEnd = curPage * 10;
		pageBegin = pageEnd - 9;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int listCnt) {
		//전체 페이지 수 구하기 (페이지당 10개의 리스트)
		pageCnt = listCnt / 10;
		if(listCnt % 10 != 0){
			pageCnt = pageCnt + 1;
		}
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getCountBlock() {
		return blockCnt;
	}

	public void setCountBlock() {
		//전체 블록 수 구하기 (블록당 10개의 페이지)
		blockCnt = pageCnt / 10;
		if(pageCnt % 10 != 0){
			blockCnt = blockCnt + 1;
		}
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}
}
