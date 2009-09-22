// Oliver Kullmann, 22.9.2009 (Swansea)

#include <sstream>
#include <iostream>
#include <cmath>

enum {parameter_error=1, conversion_error=2};

int main(const int argc, const char* const argv[]) {

  // Reading the parameters
  if (argc < 3) {
    std::cerr << "ERROR[Quadratic]: Two parameter values are needed (the coefficients b and c).\n";
    return parameter_error;
  }
  double b;
  {
    std::stringstream s; s << argv[1]; s >> b;
    if (not s) {
      std::cerr << "ERROR[Quadratic]: Conversion of parameter 1 yields an error.\n";
      return conversion_error;
    }
  }
  double c;
  {
    std::stringstream s; s << argv[2]; s >> c;
    if (not s) {
      std::cerr << "ERROR[Quadratic]: Conversion of parameter 2 yields an error.\n";
      return conversion_error;
    }
  }
  std::cout << "b = " << b << "\nc = " << c << "\n";

  // Performing the computation
  const double discriminant = b*b - 4*c;
  if (discriminant < 0) {
    std::cout << "No real roots.\n";
    return 0;
  }
  const double d = std::sqrt(discriminant);
  const double root1 = (-b+d)/2;
  const double root2 = (-b-d)/2;
  std::cout << "first root  = " << root1 << "\nsecond root = " << root2 << "\n";
}
