package org.refactoring.layerproblem.servicedependency;


import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<Member, Long> {

}