package backend.webinterface.systems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReviewSystemTest {

    @Test
    void createReview() {
        ReviewSystem reviewSystem = new ReviewSystem();

        // TODO: Add logic to create a review (e.g., set up necessary data)

        // Call the CreateReview method
        reviewSystem.CreateReview();

        // TODO: Add assertions for the CreateReview method if needed
    }

    @Test
    void getReview() {
        ReviewSystem reviewSystem = new ReviewSystem();

        // TODO: Replace with actual logic to get a review
        reviewSystem.GetReview();

        // TODO: Add assertions for the GetReview method if needed
    }

    @Test
    void listReviews() {
        ReviewSystem reviewSystem = new ReviewSystem();

        // TODO: Replace with actual logic to list reviews
        reviewSystem.ListReviews();

        // TODO: Add assertions for the ListReviews method if needed
    }

    @Test
    void submitReview() {
        ReviewSystem reviewSystem = new ReviewSystem();

        // TODO: Replace with actual logic to submit a review
        reviewSystem.SubmitReview();

        // TODO: Add assertions for the SubmitReview method if needed
    }

    @Test
    void getAvailableReviews() {
        ReviewSystem reviewSystem = new ReviewSystem();
        Integer[] result = reviewSystem.GetAvaialableReviews();

        // Assertions
        Integer[] expected = {1, 2, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    void modifyReview() {
        ReviewSystem reviewSystem = new ReviewSystem();

        // TODO: Replace with actual logic to modify a review
        Integer reviewId = 123;
        reviewSystem.ModifyReview(reviewId);

        // TODO: Add assertions for the ModifyReview method if needed
    }
}
