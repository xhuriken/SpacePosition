
/**
 * Function Fast_sorting
 *
 */

 public class fast_sorting {

    // Method to retrieve scores in the form of a 2D table: [Name, Score].
    public String[][] Datascore() {
        // New instance of the Save_score class
        Save_score save_score = new Save_score();
        // Retrieves scores as a 2D array: [Name, Score].
        String[][] scoresArray = save_score.ScorestoSimpleArray();
        return scoresArray;
    }

    // Fast sorting method for a 2D table
    public void quickSort(String[][] arr, int begin, int end, boolean isDecs) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, isDecs);

            quickSort(arr, begin, partitionIndex - 1, isDecs);
            quickSort(arr, partitionIndex + 1, end, isDecs);
        }
    }

    // Partitioning adapted for a 2D table, sorted by score
    private int partition(String[][] arr, int begin, int end, boolean isDecs) {
        int pivot = Integer.parseInt(arr[end][1]);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            int score = Integer.parseInt(arr[j][1]);
            if (isDecs) {
                if (score >= pivot) {
                    i++;

                    // Exchange arr[i] and arr[j]
                    String[] temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            } else {
                if (score <= pivot) {
                    i++;

                    // Exchange arr[i] and arr[j]
                    String[] temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // Move the pivot to its correct position
        String[] temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

}