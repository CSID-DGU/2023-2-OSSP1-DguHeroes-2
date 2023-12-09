'''
 Calculates the exponential cdf.

 @param {number} x The value.
 @returns {number} The exponential cdf.
'''


def exponential_cdf(x):
    return 1 - 2 ** -x;


'''
 Calculates the log normal cdf.

 @param {number} x The value.
 @returns {number} The log normal cdf.
'''


def log_normal_cdf(x):
    # approximation
    return x / (1 + x);


'''
 Calculates the users rank.

 @param {object} params Parameters on which the user's rank depends.
 @param {boolean} params.all_commits Whether `include_all_commits` was used.
 @param {number} params.commits Number of commits.
 @param {number} params.prs The number of pull requests.
 @param {number} params.issues The number of issues.
 @param {number} params.reviews The number of reviews.
 @param {number} params.repos Total number of repos.
 @param {number} params.stars The number of stars.
 @param {number} params.followers The number of followers.
 @returns {{level: string, percentile: number}}} The users rank.
'''


def calculateRank(all_commits, commits, prs, issues, reviews,
                  # eslint-disable-next-line no-unused-vars
                  repos,  # unused
                  stars, followers):
    COMMITS_MEDIAN = 1000 if all_commits else 250
    COMMITS_WEIGHT = 2
    PRS_MEDIAN = 50
    PRS_WEIGHT = 3
    ISSUES_MEDIAN = 25
    ISSUES_WEIGHT = 1
    REVIEWS_MEDIAN = 2
    REVIEWS_WEIGHT = 1
    STARS_MEDIAN = 50
    STARS_WEIGHT = 4
    FOLLOWERS_MEDIAN = 10
    FOLLOWERS_WEIGHT = 1

    TOTAL_WEIGHT = COMMITS_WEIGHT + PRS_WEIGHT + ISSUES_WEIGHT + REVIEWS_WEIGHT + STARS_WEIGHT + FOLLOWERS_WEIGHT

    rank = 1 - (COMMITS_WEIGHT * exponential_cdf(commits / COMMITS_MEDIAN) + PRS_WEIGHT * exponential_cdf(
        prs / PRS_MEDIAN) +
                ISSUES_WEIGHT * exponential_cdf(issues / ISSUES_MEDIAN) + REVIEWS_WEIGHT * exponential_cdf(
                reviews / REVIEWS_MEDIAN) +
                STARS_WEIGHT * log_normal_cdf(stars / STARS_MEDIAN) + FOLLOWERS_WEIGHT * log_normal_cdf(
                followers / FOLLOWERS_MEDIAN)) / TOTAL_WEIGHT

    THRESHOLDS = [1, 12.5, 25, 37.5, 50, 62.5, 75, 87.5, 100];
    LEVELS = ["S", "A+", "A", "A-", "B+", "B", "B-", "C+", "C"];
    for thres in THRESHOLDS:
        if rank < thres:
            lv = thres
    level = LEVELS[THRESHOLDS.index(lv)]

    return level, rank * 100


#print(calculateRank(False, 200, 400, 300, 50, 1, 100, 0))