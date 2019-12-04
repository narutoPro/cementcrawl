package cn.chende.seimi.repository;

import cn.chende.seimi.model.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlsRepository extends JpaRepository<Urls,Long> {


    @Query("select u from Urls  u where  u.url=?1")
    Urls findOneByUrlEquals(String url);
}
