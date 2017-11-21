package com.cqqyd2014.util.table.element;



public class Row {
	String[] fields;


	public String[] getFields() {
		return fields;
	}


	public void setFields(String[] fields) {
		this.fields = fields;
	}
	public void setField(int col_index,String value){
		fields[col_index]=value;
	}


	
}
