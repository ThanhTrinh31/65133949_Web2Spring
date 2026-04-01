package HuynhThanhTrinh_65133949_demobaomatcoban.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import HuynhThanhTrinh_65133949_demobaomatcoban.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}