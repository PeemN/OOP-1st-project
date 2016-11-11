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
		int x = 0;
		int y = 1;
		int[] currentBoxPositionX = findBox(Map, 6, x);
		int[] currentBoxPositionY = findBox(Map, 6, y);
        
        for (int i = 0; i < numberofBox; i++){
        	Map = checkNextBoxPosition(Map,currentBoxPositionX[i],currentBoxPositionY[i],inputDirection);
        }
		return Map;
	}
	
	public int[] findBox(int[][] Map,int numberofBox, int inputType){
		int[] boxPosition = new int[numberofBox];
		int height = Map[1].length;
        int width = Map[0].length;
        int i = 0;
        
		for (int r = 0; r < height; r++) {
    		for (int c = 0; c < width; c++) {
    			if (Map[c][r] == 2){
    				if (inputType == 0) {
    					boxPosition[i] = c;
    					i++;
    				} else if (inputType == 1) {
    					boxPosition[i] = r;
    					i++;
    				}
    			}
    		}
		}
			return boxPosition;
	}
	
	public int[][] checkNextBoxPosition(int[][] Map,int column,int row, int movementtype){
        int nextColumn = column;
        int nextRow = row;
        int c = 0;
        int r = 1;
        
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
        nextColumn = wallCheck(Map, nextColumn, c);
        nextRow = wallCheck(Map, nextRow, r);
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
	
		
	public int wallCheck(int[][] Map, int input, int typeofInput){
		int height = Map[1].length - 1;
        int width = Map[0].length - 1;
        if (typeofInput == 0) {
        	if (input < 0){
    			input = 0;
    		}
    		if (input > width) {
    			input = width;
    		}
        } else {
        	if (input < 0){
    			input = 0;
    		}
    		if (input > height) {
    			input = height;
    		}
        } 
		return input;
	}
}
