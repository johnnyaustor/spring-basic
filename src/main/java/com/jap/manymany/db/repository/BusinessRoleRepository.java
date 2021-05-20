/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.repository;

import com.jap.manymany.db.entity.BusinessRole;
import com.jap.manymany.db.entity.BusinessRolePkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRoleRepository extends JpaRepository<BusinessRole, BusinessRolePkey>, JpaSpecificationExecutor<BusinessRole> {
}
