public class TwoDeeListInitialization {
    public static void main(String[] args) {
        int num_rows = 6;
        int num_cols = 9;
        
        // Initialize an empty List of Lists of Strings.
        // (We could have any reference type inside the inner lists; this
        // example just chooses to use String because it's simple.)
        List<List<String>> grid = new MysteryListImplementation<List<String>>();
        
        for(int i = 0; i < num_rows; i++) {
            
            // Create an empty list for row i.
            List<String> new_row = new MysteryListImplementation<String>();
            
            for(int j = 0; j < num_cols; j++) {
                // Add a "blank String" as a stand-in, just to fill up the row.
                new_row.add(new String());
            }
            
            // Now that the row's the length we want, add it to the outer
            // list, the list of rows.
            grid.add(new_row);
        }
        
        // How many rows do we have?
        System.out.println(grid.length());
        
        // Note we have no *guarantee* that the length of every row is the same!
        // We'd like it to be the desired number of columns, but nothing about
        // our data structure guarantees that.
        //   This is a disadvantage of using the implementation (list of lists)
        // rather than the ADT (a 2-D grid of things).  For now, we're okay
        // playing a little fast and loose with our grid, but it's worth being
        // aware of precisely what kind of risk we're taking.
        System.out.println(grid.at(0).length());
    }
}
