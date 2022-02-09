package aepanchin;

    class Direction {
        public String name;
        public int deltaX;
        public int deltaY;
        public Direction previous;
        public Direction next;

        Direction(String name, int deltaX, int deltaY) {
            this.name = name;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        @Override
        public String toString() {
            StringBuilder answer = new StringBuilder();
            answer.append("[").append(this.name).append("; (").append(this.deltaX).append("; ").append(this.deltaY).append(")]");
            return answer.toString();
        }
    }


