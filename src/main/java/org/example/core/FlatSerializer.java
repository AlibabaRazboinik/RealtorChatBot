package org.example.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class FlatSerializer extends StdSerializer<Flat>{
        public FlatSerializer() {
            this(null);
        }

        public FlatSerializer(Class<Flat> t) {
            super(t);
        }

    @Override
    public void serialize(Flat flat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("city", flat.getCity());
        jsonGenerator.writeStringField("price", flat.getPrice().toString());
        jsonGenerator.writeStringField("square", flat.getSquare().toString());
        jsonGenerator.writeStringField("address", flat.getAddress());
        jsonGenerator.writeStringField("roomsAmount", flat.getRoomsAmount().toString());
        jsonGenerator.writeStringField("flatFloor", flat.getFlatFloor().toString());
        jsonGenerator.writeStringField("totalHouseFloors", flat.getTotalHouseFloors().toString());

        jsonGenerator.writeEndObject();
    }

}
