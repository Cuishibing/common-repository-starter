package cui.shibing.commonrepository.iml;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class CommonResultHandler<T> implements ResultHandler<T>{

	private T result;
	
	private Class<?> domainClass;
	
	private Class<T> rawClass;
	
	public CommonResultHandler(Class<?> domainClass, Class<T> rawClass) {
		super();
		this.domainClass = domainClass;
		this.rawClass = rawClass;
	}

	@Override
	public void handleResult(ResultContext<? extends T> resultContext) {
		
		
	}
	
	public T getResult() {
		return result;
	}

}
