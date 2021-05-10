package com.mindfire.rentingit.dto.response;

public class UploadFileResponse {
	private String successMsg;
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private byte[] byteF;
    private long size;

    public UploadFileResponse(String successMsg,String fileName, String fileDownloadUri, String fileType, byte[] data, long size) {
        this.successMsg = successMsg;
    	this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.byteF = data;
        this.size = size;
    }
    

	public String getSuccessMsg() {
		return successMsg;
	}


	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}


	public byte[] getByteF() {
		return byteF;
	}


	public void setByteF(byte[] byteF) {
		this.byteF = byteF;
	}
	
}
