
public class Sorter {

	static Contact[] conArr;

	public static void selectionSort(Contact[] contacts) {
		int n = contacts.length;
		for (int i = 0; i < n; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (contacts[j].compareTo(contacts[minIndex]) < 0)
					minIndex = j;
			}
			swap(contacts, i, minIndex);
		}
	}

	public static void insertionSort(Contact[] contacts) {
		for (int s = 2; s <= contacts.length; s++) {
			Contact toSort = contacts[s - 1];
			int i = s - 2;
			while (i >= 0 && toSort.compareTo(contacts[i]) < 0) {
				contacts[i + 1] = contacts[i];
				i--;
			}
			contacts[i + 1] = toSort;
		}
	}

	public static void quickSort(Contact[] contacts) {
		int first = 0;
		int last = contacts.length - 1;
		quickSort(contacts, first, last);
	}

	public static void quickSort(Contact[] contacts, int first, int last) {
		if (last - first >= 1) {
			int pivotIndex = partition(contacts, first, last);
			quickSort(contacts, first, pivotIndex - 1);
			quickSort(contacts, pivotIndex + 1, last);
		}
	}

	public static void mergeSort(Contact[] contacts) {
		mergeSort(contacts, 0, contacts.length - 1);
	}

	private static void mergeSort(Contact[] contacts, int lowerIndex, int higherIndex) {
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;

			mergeSort(contacts, lowerIndex, middle);
			mergeSort(contacts, middle + 1, higherIndex);
			merge(contacts, lowerIndex, middle, higherIndex);
		}
	}

	public static void merge(Contact[] contacts, int low, int middle, int high) {
		Contact[] temp = new Contact[high + 1];

		for (int i = low; i <= high; i++)
			temp[i] = contacts[i];

		int i = low;
		int j = middle + 1;
		int k = low;

		while (i <= middle && j <= high) {
			if (temp[i].compareTo(temp[j]) <= 0) {
				contacts[k] = temp[i];
				i++;
			} else {
				contacts[k] = temp[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			contacts[k] = temp[i];
			k++;
			i++;
		}
	}

	static int partition(Contact[] contacts, int low, int high) {
		int piv = low;
		Contact pivot = contacts[low];

		do {
			while (low <= high && contacts[low].compareTo(pivot) <= 0)
				low++;
			while (contacts[high].compareTo(pivot) > 0)
				high--;
			if (low < high)
				swap(contacts, low, high);
		} while (low < high);

		swap(contacts, piv, high);
		piv = high;

		return piv;
	}

	private static Contact[] swap(Contact[] contacts, int low, int high) {
		Contact temp = contacts[low];
		contacts[low] = contacts[high];
		contacts[high] = temp;
		return contacts;
	}
}