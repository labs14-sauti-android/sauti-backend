package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Question;
import com.lambdaschool.starthere.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "quoteService")
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository quoterepos;

    @Override
    public List<Question> findAll()
    {
        List<Question> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Question findQuoteById(long id)
    {
        return quoterepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (quoterepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (quoterepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                quoterepos.deleteById(id);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Question save(Question question)
    {
        return quoterepos.save(question);
    }

    @Override
    public List<Question> findByUserName(String username)
    {
        List<Question> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
