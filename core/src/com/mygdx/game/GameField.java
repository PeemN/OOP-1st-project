package com.mygdx.game;

import java.util.Random;

public class GameField {
//mapcreator
	public int[][] mapCreator() {
		int Map[][] = new int[6][6];
        int numberofHole = 6;
        int numberofBox = 6;
        int numberofRock = 6;
        int codeHole = 1;
        int codeBox = 2;
        int codeRock = 3;
        
        objectMarker(Map,numberofHole,codeHole);
        objectMarker(Map,numberofBox,codeBox);
        objectMarker(Map,numberofRock,codeRock);
		return Map;
	}
	
	public int[][] objectMarker(int[][] Map, int numberofObject, int typeofObject) {
		Random random = new Random();
		int height = Map[1].length;
        int width = Map[0].length;
		for (int i = 0; i < numberofObject; ) {
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = typeofObject;
				i++;
			}
		}
		return Map;
	}
}
