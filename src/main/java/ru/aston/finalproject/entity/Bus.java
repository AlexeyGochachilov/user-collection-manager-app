package ru.aston.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Bus implements Comparable<Bus> {

    private final String model;
    private final String mileageInKilometers;
    private final int number;

    private Bus(Entity entity){
        this.model = entity.getFieldOne();
        this.mileageInKilometers = entity.getFieldTwo();
        this.number = entity.getFieldInt();
    }

    public static Bus build(String model, String mileageInKilometers, int number) {
        BuildConcreteEntity buildBusEntity = new BuildConcreteEntity();
        Entity busEntity = buildBusEntity.buildBus(model, mileageInKilometers, number);
        return new Bus(busEntity);
    }

    @Override
    public int compareTo(Bus o) {
        if (!this.model.equals(o.model)) {
            return this.model.compareTo(o.model);
        } else if (!this.mileageInKilometers.equals(o.mileageInKilometers)) {
            return this.mileageInKilometers.compareTo(o.mileageInKilometers);
        } else {
            return Integer.compare(this.number, o.number);
        }
    }

    @Override
    public String toString() {
        return String.format
                ("Bus{model: %s, mileageInKilometers: %s, number: %d}",
                        model, mileageInKilometers, number);
    }
}
