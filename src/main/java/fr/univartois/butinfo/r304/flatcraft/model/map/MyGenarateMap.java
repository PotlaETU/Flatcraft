package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.GameMap;

import java.util.Random;

public class MyGenarateMap {
    private int heigth;
    private int width;

    private final Random RANDOM = new Random();
    private CellFactory factory;

    public MyGenarateMap(int heigth, int width) {
        this.heigth = heigth;
        this.width = width;
    }

    public GameMap createMap() {
        int soilHeigth = RANDOM.nextInt(heigth);
        SimpleGameMap map = new SimpleGameMap(heigth, width, soilHeigth);
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if (i < soilHeigth) {
                    Cell cell = factory.createSubSoil();
                    map.setAt(i, j, cell);
                } else if (i == soilHeigth) {
                    Cell cell = factory.createSoilSurface();
                    map.setAt(i, j, cell);
                } else {
                    Cell cell = factory.createSky();
                    map.setAt(i, j, cell);
                }
            }
        }
        return map;
    }

}