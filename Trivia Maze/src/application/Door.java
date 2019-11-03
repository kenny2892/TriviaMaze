package application;

public class Door
{
	private boolean isLocked;
	private Question question;

	public Door(boolean isLocked, Question question)
	{
		if(question == null)
			throw new IllegalArgumentException("The question parameter was null.");
		
		this.isLocked = isLocked;
	}

	public Question getQuestion()
	{
		return this.question;
	}

	public boolean isLocked()
	{
		return this.isLocked;
	}

	public void setLocked(boolean isLocked)
	{
		this.isLocked = isLocked;
	}
}
