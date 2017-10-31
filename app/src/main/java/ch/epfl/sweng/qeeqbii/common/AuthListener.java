package ch.epfl.sweng.qeeqbii.common;

public interface AuthListener {

	public void onAuthSucceed();

	public void onAuthFail(String error);
}
