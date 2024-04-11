package tech.jamersondev.streams.exceptions;

public class TitleEpsodioNotFoundException extends RuntimeException{
    public TitleEpsodioNotFoundException(String title) {
        super(String.format("Epsódio %s não encontrado", title));
    }
}
