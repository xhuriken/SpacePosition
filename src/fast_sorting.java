public class fast_sorting {

    int Arr[] = {5, 9, 4, 6, 5, 3};



    public void Datascore(){
        // Nouvelle instance de la classe Save_score
        Save_score save_score = new Save_score();
        // Permet de récupérer les scores sous forme de tableau 2D : [Nom, Score]
        String[][] scoresArray = save_score.ScorestoSimpleArray();
    
        // Pour afficher le tableau
        for (String[] entry : scoresArray) {
            System.out.println( entry[0] +" : "+entry[1]);
        }
    }
    

    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    public static void main(String[] args) {
        // Création d'une nouvelle instance de la classe fast_sorting
        fast_sorting sorter = new fast_sorting();
        Save_score score = new Save_score();
        sorter.Datascore();
    }
}