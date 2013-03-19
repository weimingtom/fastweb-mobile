package com.supermy.mongodb.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.supermy.mongodb.domain.Person;

/**
 * 
 * @author jamesmo
 *
 */
public interface PersonRepository extends MongoRepository<Person, ObjectId> {
	// 查询大于age的数据
	public Page<Person> findByAgeGreaterThan(int age, Pageable page);

	@Query("{ 'name':{'$regex':?2,'$options':'i'}, sales': {'$gte':?1,'$lte':?2}}")
	public Page<Person> findByNameAndAgeRange(String name, double ageFrom,
			double ageTo, Pageable page);

	@Query(value = "{ 'name':{'$regex':?2,'$options':'i'}, sales':{'$gte':?1,'$lte':?2}}", fields = "{ 'name' : 1, 'age' : 1}")
	public Page<Person> findByNameAndAgeRangeMore(String name, double ageFrom,
			double ageTo, Pageable page);

}
