package com.cqqyd2014.util.table;

import com.cqqyd2014.util.table.element.Head;
import com.cqqyd2014.util.table.element.Row;


import net.sf.json.JSONObject;


public class Table {
	Head head;
	java.util.ArrayList<Row> rows;
	public Table() {
		
		super();
		// TODO Auto-generated constructor stub
		head=new Head();
		rows=new java.util.ArrayList<>();
	}
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public java.util.ArrayList<Row> getRows() {
		return rows;
	}
	public void setRows(java.util.ArrayList<Row> rows) {
		this.rows = rows;
	}
	public void setField(int row_index,int col_index,String value){
		Row row=rows.get(row_index);
		row.setField(col_index, value);
	}

	public void setHead(String[] head_str){
		head.setCols(head_str);
	}
	public String getHeadColsIndex(int index) throws Exception{
		
		if (head.getCols()==null){
			throw new Exception("表格没有列标题");
		}
		else{
			String cols[]= head.getCols();
			if (index>=cols.length){
				throw new Exception("超出表格列边界");
			}
			return cols[index];
		}
	}
	public Row getRowIndex(int row_index){
		return rows.get(row_index);
	}
	public String getRowFied(int row_index,String col_name) throws Exception{
		int col_index=getColIndex(col_name);
		return getRowFied(row_index,col_index);
		
	}
	public String getRowFied(int row_index,int col_index) throws Exception{
		if (rows.size()==0){
			throw new Exception("表格没有行数据");
		}
		if (rows.size()<=row_index){
			throw new Exception("超出表格行边界");
		}
		Row row=rows.get(row_index);
		String[] fields=row.getFields();
		if (fields.length<=col_index){
			throw new Exception("超出表格第"+(row_index+1)+"行的列边界");
		}
		return fields[col_index];
		
	}
	public int getRowCount(){
		return rows.size();
	}
	public int getColCount(){
		return head.getCols().length;
	}
	//返回json对象
	public JSONObject toJsonObject(){

		
		JSONObject ja1 = JSONObject.fromObject(this);
		return ja1;
	}
	//返回制定列为某值的行
	public java.util.ArrayList<Row> getRowFilter(int index_col,String value) throws Exception{
		java.util.ArrayList<Row> return_rows=new java.util.ArrayList<>();
		for (int i=0;i<rows.size();i++){
			String temp_value=getRowFied(i,index_col);
			if (temp_value.equals(value)){
				return_rows.add(rows.get(i));
			}
		}
		return return_rows;
		
	}
	public java.util.ArrayList<Row> getRowFilter(String col_name,String value) throws Exception{
		int col_index=getColIndex(col_name);
		return getRowFilter(col_index,value);
		
		
		
	}
	//得到列名对应的index
	public int getColIndex(String col_name) throws Exception{
		int i=0;
		String[] cols=head.getCols();
		for (;i<head.getCols().length;i++){
			
			if (cols[i].equals(col_name)){
				return i;
			}
			
		}
		throw new Exception("表格中找不到列为"+col_name+"的数据");
	}
	
}
