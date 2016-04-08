package com.sis.mrep.client.content.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

public class StaticMaps {

	public static Map<Integer, Integer> callsForTier = new HashMap<Integer, Integer>();
	static {
		callsForTier.put(1, 1);
		callsForTier.put(2, 2);
		callsForTier.put(3, 3);
		callsForTier.put(4, 4);
		callsForTier.put(5, 5);
		callsForTier.put(6, 6);
		callsForTier.put(7, 7);
		callsForTier.put(8, 8);
		callsForTier.put(9, 9);
	}
	
	public static Map<Integer, String> tierMap = new HashMap<Integer, String>();
	static {
		tierMap.put(1, "HH");
		tierMap.put(2, "MM");
		tierMap.put(3, "LL");
		tierMap.put(4, "HM");
		tierMap.put(5, "HL");
		tierMap.put(6, "MH");
		tierMap.put(7, "ML");
		tierMap.put(8, "LH");
		tierMap.put(9, "LM");
	}
	
	
	public static Map<Integer, String> specialityMap = new HashMap<Integer, String>();
	static {
		specialityMap.put(1, "Cardialoty");
		specialityMap.put(2, "Bateny");
	}
	
	//private static ListBox tierLB = new ListBox();
	
	//private static ListBox specLB = new ListBox();
	
	public static ListBox getTierLB(){
		ListBox tierLB = new ListBox();
		Iterator<Integer> i = tierMap.keySet().iterator();
		while (i.hasNext()){
			int id = i.next();
			tierLB.addItem(tierMap.get(id), ""+id);
		}
		return tierLB;
	}
	
	public static ListBox getSpecLB(){
		ListBox specLB = new ListBox();
		Iterator<Integer> i = specialityMap.keySet().iterator();
		while (i.hasNext()){
			int id = i.next();
			specLB.addItem(specialityMap.get(id), ""+id);
		}
		return specLB;
	}

	public static Map<Integer, String> companyFeedbackMap = new HashMap<Integer, String>();
	static {
		companyFeedbackMap.put(1, "Good");
		companyFeedbackMap.put(2, "Normal");
		companyFeedbackMap.put(3, "Bad");
	}
	
	public static ListBox getCompanyFeedback(){
		ListBox companyFeedbackLB = new ListBox();
		Iterator<Integer> i = companyFeedbackMap.keySet().iterator();
		while (i.hasNext()){
			int id = i.next();
			companyFeedbackLB.addItem(companyFeedbackMap.get(id), ""+id);
		}
		return companyFeedbackLB;
	}
	
	
	public static ListBox getMaterials(){
		ListBox materialsLB = new ListBox();
		materialsLB.addItem("Flayers" ,"1");
		materialsLB.addItem("Catalog" , "2");
		return materialsLB;
	}
	
	
}
