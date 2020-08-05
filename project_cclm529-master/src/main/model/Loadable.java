package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    ArrayList load(String filename) throws IOException, ClassNotFoundException;
}
