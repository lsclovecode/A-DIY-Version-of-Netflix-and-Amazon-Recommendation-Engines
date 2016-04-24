public class DirectorsFilter implements Filter {
	private String dr;
	public DirectorsFilter(String g){
		dr=g;
	}
	@Override
	public boolean satisfies(String id) {
		String[] output= dr.split(",");
		String drforthis= MovieDatabase.getDirector(id);
		String[] input= drforthis.split(",");
        for(String s : output){
			for(String sj: input){
				if (s.equals(sj)) return true;
			} 
		}		
		return false;
	}

}