package dao;

import entity.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)

public class JobDAOImplTest {

    @Mock
    private SessionFactory hibernateSessionFactory;

    @Mock
    private JobDAO jobDAO;

    @InjectMocks
    private JobDAOImpl jobDAOImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPendingJobs() {

        Session session = Mockito.mock(Session.class);
        Query query = Mockito.mock(Query.class);

        Mockito.when(hibernateSessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.createQuery("some query")).thenReturn(query);

        List<Job> pendingJobsList = new ArrayList<>();
        Mockito.when(query.getResultList()).thenReturn(pendingJobsList);
    }



}
