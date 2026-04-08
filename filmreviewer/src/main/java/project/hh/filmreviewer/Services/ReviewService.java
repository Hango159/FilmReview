package project.hh.filmreviewer.Services;
import project.hh.filmreviewer.Repository.ReviewsRepository;
import project.hh.filmreviewer.Model.Reviews;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewsRepository reviewsRepository;

        public ReviewService(ReviewsRepository reviewsRepository) {
            this.reviewsRepository = reviewsRepository;
        }

        public List<Reviews> getAllReviews() {
            return reviewsRepository.findAll();
        }

        public Reviews getReviewById(Long id) {
            return reviewsRepository.findById(id).orElse(null);
        }

        public Reviews addReview(Reviews review) {
            return reviewsRepository.save(review);
        }

        public void deleteReview(Long id) {
            reviewsRepository.deleteById(id);
        }

}
