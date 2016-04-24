public class MinutesFilter implements Filter {
	private int minM;
	private int maxM;
	public MinutesFilter(int min, int max){
		minM=min;
		maxM=max;
	}
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getMinutes(id)>=minM && MovieDatabase.getMinutes(id)<=maxM;
	}

}
