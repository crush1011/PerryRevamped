package rc.CastroOrnelas.FRC;

public interface RSensor<type, returnType>{
	public void reset();
	public returnType getValue();
	public type getSensor();
}
