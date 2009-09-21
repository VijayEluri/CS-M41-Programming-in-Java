// Oliver Kullmann, 19.9.2009 (Swansea)

#include <iostream>

int main(const int argc, const char* const argv[]) {
  if (argc == 1) {
    std::cerr << "ERROR[UseArgument]: One parameter is needed!\n";
    return 1;
  }
  std::cout << "Hello, Mr. " << argv[1] << ". How is it going?\n";
}
