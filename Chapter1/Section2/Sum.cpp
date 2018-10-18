#include <iostream>
#include <string>
#include <limits>

#include <cstdint>
#include <cstdlib>

int main(const int argc, const char* const argv[]) {
  typedef std::uint32_t uint32;
  typedef std::uint_fast64_t uint64;
  const auto N0 = std::stoul(argv[1]);
  if (N0 > std::numeric_limits<uint32>::max()) {
    std::cerr << "Input " << argv[1] << " too large!\n";
    std::exit(1);
  }
  const uint32 N = N0;
  uint64 sum = 0;
  for (uint32 i = 0; i < N;) sum += ++i;
  std::cout << sum << "\n";
}
