package ru.aston.finalproject.environment.appdata;

import lombok.Getter;
import lombok.Setter;
import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.service.counters.MultithreadCounter;
import ru.aston.finalproject.service.filters.FilterList;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.DataLoader;
import ru.aston.finalproject.service.loader.LoaderService;
import ru.aston.finalproject.service.sorting.MergeSorter;
import ru.aston.finalproject.service.sorting.Sorter;
import ru.aston.finalproject.service.sorting.StrangeSorter;
import ru.aston.finalproject.service.writer.FileWriter;
import ru.aston.finalproject.service.writer.Writer;

import java.util.List;

@Setter
@Getter
public abstract class AppData<T> {

    protected List<T> entityList = new CustomArrayList<>();
    protected final Sorter sorter = new MergeSorter();
    protected final StrangeSorter strangeSorter = new StrangeSorter(sorter);

    protected final Parsing<T> parser;
    protected final LoaderService<T> loaderService;
    protected final DataLoader<T> fileDataLoader;
    protected final DataLoader<T> consloeDataLoader;
    protected final Writer<T> fileWriter;
    protected final MultithreadCounter<T> entryCounter;
    protected final FilterList<T> filter;

    public AppData(Parsing<T> parser, LoaderService<T> loaderService,
                   DataLoader<T> fileDataLoader, FilterList<T> filter) {

        this.parser = parser;
        this.loaderService = loaderService;
        this.fileDataLoader = fileDataLoader;
        this.consloeDataLoader = new ConsoleDataLoader<>(parser);
        this.fileWriter = new FileWriter<>(parser);
        this.entryCounter = new MultithreadCounter<>();
        this.filter = filter;
    }
}
