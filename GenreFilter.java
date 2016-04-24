public class GenreFilter implements Filter {
	private String genre;
	public GenreFilter(String g){
		genre=g;
	}
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).indexOf(genre)!=-1;
	}

}
