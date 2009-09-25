// Oliver Kullmann, 25.9.2009 (Swansea)

#include <iostream>
#include <cstdlib>
#include <sstream>
#include <cmath>

namespace {

  enum {too_few_arguments=1, too_many_arguments=2, conversion_error=3,
    invalid_parameters=4
  };

  inline bool random_bit() {
    return (std::rand() <= RAND_MAX/2) ? false : true;
  }

  inline unsigned int string2int(const char* const string) {
    std::stringstream s;
    s << string;
    if (not s) {
      std::cerr << "ERROR[Gamber]: All parameters must be integers.\n";
      std::exit(conversion_error);
    }
    unsigned int i;
    s >> i;
    return i;
  }

}

int main(const int argc, const char* const argv[]) {

  // Reading the parameters
  if (argc < 4) {
    std::cerr << "ERROR[Gamber]: At least three arguments are expected (stake,"
    " goal, and the number of repetitions).\n";
    return too_few_arguments;
  }
  if (argc > 5) {
    std::cerr << "ERROR[Gamber]: At most four arguments are expected (stake,"
    " goal, number of repetitions, and the seed for the random generator).\n";
    return too_many_arguments;
  }
  const unsigned int stake =  string2int(argv[1]);
  const unsigned int goal = string2int(argv[2]);
  if (stake == 0) {
    std::cerr << "ERROR[Gamber]: The stake must not be zero.\n";
    return invalid_parameters;
  }
  if (stake > goal) {
    std::cerr << "ERROR[Gamber]: The stake can be at most the goal.\n";
    return invalid_parameters;
  }
  const unsigned int N = string2int(argv[3]);
  if (N == 0) return 0;
  if (argc == 5)
    std::srand(string2int(argv[4]));

  // Performing the experiment

  unsigned int wins = 0;
  double max_steps = 0;
  double sum_steps = 0;
  double sum_square_steps = 0;

  for (unsigned int t = 0; t < N; ++t) {
    unsigned int cash = stake;
    double steps = 0;
    while (cash != 0 and cash != goal) {
      if (random_bit()) ++cash;
      else --cash;
      ++steps;
    }
    if (cash == goal) ++wins;
    if (steps > max_steps) max_steps = steps;
    sum_steps += steps;
    sum_square_steps += steps*steps;
  }

  // Analysing the outcome
  std::cout << "Stake = " << stake << ", goal = " << goal << ", number of trials = " << N << ".\n";
  std::cout << "Probability of winning = " << double (stake) / goal << ".\n";
  std::cout << "Expected number of repetitions = " << double (stake) * (goal - stake) << ".\n";
  std::cout << "Number of wins = " << wins << "; the frequency is " << double (wins) / N << ".\n";
  std::cout << "Average number of steps = " << sum_steps / N << ", while "
  "the maximum is " << max_steps << ".\n";
  if (N > 1)
    std::cout << "The standard deviation is " << std::sqrt((N * sum_square_steps - sum_steps*sum_steps) / N / (N-1)) << ".\n";

}
