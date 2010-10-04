package com.sorca.views.utils;


import java.util.ArrayList;

import com.sorca.model.beans.LinePropertyBean;
import com.sorca.model.beans.Model;

public class ProcessUtils {



	public static ArrayList<LinePropertyBean> searchForChild(
			ArrayList<LinePropertyBean> linee, int startCounter, LinePropertyBean parent) {


		LinePropertyBean current = linee.get(startCounter);

		if(parent != null){
			current.parent = parent;
			parent.child.add(current);

		}

		LinePropertyBean next;
		try{
			next = linee.get(startCounter+1);
		}catch (IndexOutOfBoundsException e) {
			return linee;
		}


		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());

		if(currentIndex < nextIndex){
			int childCounter = startCounter+1;

			searchForChild(linee,childCounter,current);

			linee.remove(childCounter);

		}else if(currentIndex == nextIndex){
			searchForChild(linee,startCounter+1,parent);
			linee.remove(startCounter);

		}else if(currentIndex > nextIndex){


			//INDIVIDUA il parent dal livello
			LinePropertyBean locParent = getParentFromIndex(current,next,parent);

			searchForChild(linee,startCounter+1,locParent);
			if(next.parent != null){
				linee.remove(startCounter);
			}
		}

		return linee;
	}

	public static LinePropertyBean getParentFromIndex(
			LinePropertyBean current, LinePropertyBean next, LinePropertyBean parent) {


		LinePropertyBean locParent = Model.getParentLine();

		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());


		try{

			int indexDifference = currentIndex - nextIndex;


			if(indexDifference== 1){
				locParent = parent;
			}
			if(indexDifference== 2 || indexDifference== 3){
				locParent = parent.parent;
			}

			if(indexDifference== 4 || indexDifference== 5){
				locParent = parent.parent.parent;
			}

			if(indexDifference== 6 || indexDifference== 7){
				locParent = parent.parent.parent.parent;
			}

			if(indexDifference== 8 || indexDifference== 9){
				locParent = parent.parent.parent.parent.parent;
			}

			if(indexDifference== 10 || indexDifference== 11){
				locParent = parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 12 || indexDifference== 13){
				locParent = parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 14 || indexDifference== 15){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 16 || indexDifference== 17){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 18 || indexDifference== 19){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 20 || indexDifference== 21){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent.parent.parent.parent;
			}


		}catch (NullPointerException e) {
			if(locParent == null){
				locParent = Model.getParentLine();
			}
		}

		//TODO TROVARE UN MODO MIGLIORE
		//IF 01 then parent is root
		if(nextIndex == 1 && locParent != null){
			return Model.getParentLine();
		}

		return locParent;
	}

	public static void countFiller() {

		int count = 0;
		for(int x = 0; x< Model.getLinee().size(); x++){
			LinePropertyBean linea = Model.getLinee().get(x);
			//Not redefines
			if(!linea.hasRedefines()){
				count = count + linea.getChildsPicValue();
			}
		}

		Model.getFileBean().setCount(String.valueOf(count));
	}

}
