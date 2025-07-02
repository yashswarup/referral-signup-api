
package com.example.referral.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.referral.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByReferredByIdAndProfileCompletedTrue(Long referredById);
    List<User> findByReferrerId(Long referrerId);
    Optional<User> findByReferralCode(String referralCode);
}
