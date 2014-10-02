package compute;

import java.io.Serializable;

public interface Task extends Serializable {
	public String echo(String input);
}
