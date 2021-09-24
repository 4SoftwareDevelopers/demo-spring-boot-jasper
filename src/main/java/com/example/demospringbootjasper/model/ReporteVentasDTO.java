package com.example.demospringbootjasper.model;

import java.io.ByteArrayInputStream;

/**
 * 
 * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
 * @project demo-spring-boot-jasper
 * @class ReporteVentasDTO
 * @description 
 * @HU_CU_REQ 
 * @date 24 sep. 2021
 */
public class ReporteVentasDTO {

	private String fileName;
	private ByteArrayInputStream stream;
	private int length;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ByteArrayInputStream getStream() {
		return stream;
	}

	public void setStream(ByteArrayInputStream stream) {
		this.stream = stream;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
