package br.edu.ifms.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable{
	
	private @Inject EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception{
		EntityTransaction transaction = manager.getTransaction();
		boolean owner = false;
		
		try {
			if(!transaction.isActive()) {
				transaction.begin();
				transaction.rollback();
				transaction.begin();
				owner = true;
			}
			return context.proceed();
		}
		catch(Exception ex) {
			if(transaction != null && owner) {
				transaction.rollback();
			}
			throw ex;
		}
		finally {
			if(transaction != null && transaction.isActive() && owner)
				transaction.commit();
		}
	}

}
