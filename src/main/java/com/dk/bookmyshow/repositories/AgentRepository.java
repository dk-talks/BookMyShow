package com.dk.bookmyshow.repositories;

import com.dk.bookmyshow.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer>
{
}
