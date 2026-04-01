package HuynhThanhTrinh_65133949_demobaomatcoban.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import HuynhThanhTrinh_65133949_demobaomatcoban.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}