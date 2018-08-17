package it.polimi.deib.ssp.utils;

import it.polimi.yasper.core.spe.windowing.assigner.WindowAssigner;
import it.polimi.yasper.core.stream.RegisteredStream;
import it.polimi.yasper.core.stream.StreamElement;

import java.util.ArrayList;
import java.util.List;

public class WritableStream implements RegisteredStream {

    List<WindowAssigner> assigners = new ArrayList<>();

    public void put(Elem e) {
        assigners.forEach(windowAssigner -> windowAssigner.notify(e));
    }

    @Override
    public String getURI() {
        return null;
    }

    @Override
    public void addWindowAssiger(WindowAssigner windowAssigner) {
        assigners.add(windowAssigner);
    }

    @Override
    public void put(StreamElement e) {
        assigners.forEach(a -> a.notify(e));
    }

    public static class Elem implements StreamElement {

        private long timestamp;
        private Object content;

        public Elem(long i, Object c) {
            timestamp = i;
            content = c;

        }

        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public Object getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "(" + getContent().toString() + "," + timestamp + ") " + hashCode();
        }
    }
}
