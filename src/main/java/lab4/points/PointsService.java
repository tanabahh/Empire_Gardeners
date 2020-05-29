package lab4.points;

import lab4.users.UserEntity;
import lab4.users.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PointsService {

    private final PointRepository repository;
    private final UsersRepository usersRepository;

    PointsService(PointRepository repository, UsersRepository usersRepository) {
        this.repository = repository;
        this.usersRepository = usersRepository;
    }

    public PointEntity addPoint(PointEntity point) {
        System.out.println(point.getX());
        System.out.println(point.getY());
        System.out.println(point.getR());
        System.out.println(point.isHit());
        return repository.save(new PointEntity(usersRepository.getOne(point.getUser().getUsername()),
                point.getX(), point.getY(), point.getR(), point.isHit(), point.getCreated()));
    }

    public List<PointEntity> getPoints(String username) {
        final UserEntity userEntity = usersRepository.getOne(username);

        return repository.findByUserOrderByCreatedDesc(userEntity);
    }

    public void addPoint(double x, double y, double r, boolean hit, String name) {
        UserEntity user = usersRepository.getOne(name);
        PointEntity point = new PointEntity(user, x, y, r, hit, LocalDateTime.now());
        repository.save(point);
    }
}
