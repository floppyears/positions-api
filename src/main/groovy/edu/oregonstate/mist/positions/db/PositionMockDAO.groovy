package edu.oregonstate.mist.positions.db

import edu.oregonstate.mist.positions.core.Position

class PositionMockDAO extends PosDAO implements PositionDAO {
    private static List<String> titles = ["Office Manager", "Retail Food Service",
                                          "Mock Dept", "Science Lab"]
    public int positionSize = 0

    PositionMockDAO(int positionSize) {
        this.positionSize = positionSize
    }

    @Override
    List<Position> getPositions(String businessCenter) {
        if (businessCenter == "empty") {
            return generate(0, null)
        }

        generate(positionSize, businessCenter)
    }

    static List<Position> generate(int size, String businessCenter) {
        List<Position> result = new ArrayList<>()
        if (size) {
            size.times {

                // add two positions so to the same dept
                result += singlePosition(it, businessCenter)
                result += singlePosition(it, businessCenter)
            }
        }
        result
    }

    private static Position singlePosition(int it, String businessCenter) {
        def random = new Random()
        new Position(
                title: chooseTitle(),
                businessCenter: businessCenter,
                positionNumber: "C5" + random.nextInt(9999).toString(),
                organizationCode: 1111 + it
        )
    }

    private static String chooseTitle() {
        def random = new Random()
        titles[random.nextInt(titles.size())] + " " +
                random.nextInt(111)
    }

    boolean isValidBC(String businessCenter) {
        def invalidBusinessCenters = ["empty", "invalid-bc"]
        !invalidBusinessCenters.contains(businessCenter)
    }

    @Override
    void close() { }

    @Override
    Integer checkHealth() {
        return null
    }
}
