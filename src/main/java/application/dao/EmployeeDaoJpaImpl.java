package application.dao;

import application.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoJpaImpl(EntityManager theEntityManager) {
        entityManager =  theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create the query
        Query theQuery = entityManager.createQuery("from Employee");

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // get the employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        // save or update employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        theEmployee.setId(dbEmployee.getId());


    }

    @Override
    public void deleteById(int theId) {
        //delete object with a primary key
        Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId",theId);

        theQuery.executeUpdate();
    }
}
