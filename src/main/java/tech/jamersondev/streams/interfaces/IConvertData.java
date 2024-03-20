package tech.jamersondev.streams.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass) throws JsonProcessingException;
}
