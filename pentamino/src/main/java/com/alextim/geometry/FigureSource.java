package com.alextim.geometry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FigureSource {

    @Setter(AccessLevel.NONE)
    private List<Figure> figures;

    public FigureSource(String fileName) throws FileNotFoundException {
        this.figures = convert(readData(fileName));
    }

    private List<List<String>> readData(String fileName) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(fileName),
                new TypeToken<List<List<String>>>() {}.getType());
    }

    private List<Figure> convert(List<List<String>> data) {
        return data.stream().map(strings -> new Figure(Figure.toCoords(strings))).collect(Collectors.toList());
    }
}
