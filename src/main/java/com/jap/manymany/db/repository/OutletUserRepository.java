/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.repository;

import com.jap.manymany.db.entity.OutletUser;
import com.jap.manymany.db.entity.OutletUserPkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletUserRepository extends JpaRepository<OutletUser, OutletUserPkey>, JpaSpecificationExecutor<OutletUser> {
}
