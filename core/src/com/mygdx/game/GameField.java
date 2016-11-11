package com.mygdx.game;

import java.util.Random;

public class GameField {
	public static int numberofHole = 6;
	public static int numberofBox = 6;
	public static int numberofRock = 6;
	public static final int codeBlank = 0;
	public static final int codeHole = 1;
    public static final int codeBox = 2;
    public static final int codeRock = 3;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
	
	public int[][] mapCreator() {
		int Map[][] = new int[6][6];

        objectMarker(Map,numberofHole,codeHole);
        objectMarker(Map,numberofBox,codeBox);
        objectMarker(Map,numberofRock,codeRock);
		return Map;
	}
	
	public int[][] objectMarker(int[][] Map, int numberofObject, int typeofObject) {
		Random random = new Random();
		int height = Map[1].length;
        int width = Map[0].length;
        
		for(int i = 0; i < numberofObject; ) {
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = typeofObject;
				i++;
			}
		}
		return Map;
	}
	
	public int[][] mapUpdate(int[][] Map, int inputDirection) {
		int[] currentBoxPositionX = findBoxX(Map, 6);
		int[] currentBoxPositionY = findBoxY(Map, 6);
        
        for (int i = 0; i < 1/*numberofBox*/;i++){
        	Map = checkNextBoxPosition(Map,currentBoxPositionX[i],currentBoxPositionY[i],inputDirection);
        }
		return Map;
	}
	
	public int[] findBoxX(int[][] Map,int numberofBox){
		int[] boxPositionX = new int[numberofBox];
		int height = Map[1].length;
        int width = Map[0].length;
        int i = 0;
        
		for (int r = 0; r < height; r++) {
    		for (int c = 0; c < width; c++) {
    			if (Map[c][r] == 2){
    				boxPositionX[i] = c;
    				i++;
    			}
    		}
		}
			return boxPositionX;
	}
	
	public int[] findBoxY(int[][] Map,int numberofBox){
		int[] boxPositionY = new int[numberofBox];
		int height = Map[1].length;
        int width = Map[0].length;
        int j = 0;
        
		for (int r = 0; r < height; r++) {
    		for (int c = 0; c < width; c++) {
    			if (Map[c][r] == 2){
    				boxPositionY[j] = r;
    				j++;
    			}
    		}
		}
			return boxPositionY;
	}
	public int[][] checkNextBoxPosition(int[][] Map,int column,int row, int movementtype){
		int height = Map[1].length - 1;
        int width = Map[0].length - 1;
        int nextColumn = column;
        int nextRow = row;
        
        switch (movementtype){
        case DIRECTION_UP:
        	nextRow -= 1;
        	break;
        case DIRECTION_RIGHT:
        	nextColumn += 1;
        	break;
        case DIRECTION_DOWN:
        	nextRow += 1;
        	break;
        case DIRECTION_LEFT:
        	nextColumn -= 1;
        	break;
        }
		if (nextColumn < 0){
			nextColumn = 0;
		}
		if (nextColumn > width){
			nextColumn = width;
		}
		if (nextRow < 0){
			nextRow = 0;
		}
		if (nextRow > height){
			nextRow = height;
		}
		switch (Map[nextColumn][nextRow]){
		case codeBlank:
			Map[column][row] = 0;
			Map[nextColumn][nextRow] = 2;
            break;
		case codeBox:
            break;
        case codeRock:
            break;
		case codeHole:
			Map[column][row] = 0;
			Map[nextColumn][nextRow] = 0;
			
			break;
		}
		return Map;
	}
		
}
